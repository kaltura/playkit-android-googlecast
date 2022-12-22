fail() {
    echo "$@" 1>&2
    exit 1
}

checkout() {
    echo Checking out newtag = "$NEW_TAG", release type = "$RELEASE_TYPE"
    git fetch --all

    case $RELEASE_TYPE in
      Full)
          git checkout -b "$BRANCH_NAME" || fail "Unable to checkout $BRANCH_NAME";;
      Patch)
          git checkout "$BRANCH_NAME" || fail "Unable to checkout $BRANCH_NAME";;
      Update)
          git checkout -b "$BRANCH_NAME" "$PREV_TAG" || fail "Unable to checkout $BRANCH_NAME";;
    esac
}

set_version() {
    echo Setting version of "$REPO_NAME" to "$NEW_VERSION"

    # Changing the version in version.gradle file
    perl -pi -e "s/^ext.libVersion.*$/ext.libVersion = '$NEW_VERSION'/" $VERSION_FILE
}

build() {
    chmod +x gradlew
    ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
}

release_and_tag() {
    git config user.name "Github Actions Bot KLTR"
    git config user.email "<>"

    echo Releasing version $NEW_VERSION of $REPO_NAME to GitHub
    set +e
    git add $VERSION_FILE
    git commit -m "Update version to $NEW_TAG"
    set -e
    git push origin HEAD:$BRANCH_NAME

    if [[ "$RELEASE_TYPE" = "Patch" || "$RELEASE_TYPE" = "Full" ]]; then

cat << EOF > ./post.json
{
      "name": "$NEW_TAG",
      "body": "## Changes from [$PREV_TAG](https://github.com/kaltura/$REPO_NAME/releases/tag/$PREV_TAG)\n\nTBD",
      "tag_name": "$NEW_TAG",
      "target_commitish": "$BRANCH_NAME"
}
EOF
    fi

    if [ "$RELEASE_TYPE" = "Update" ]; then
                  JSON_BODY="### Netkit Plugin Support\n\n"
                  JSON_BODY="$JSON_BODY$NEW_TAG\n\n"
      JSON_BODY="$JSON_BODY * upgrade to $NEW_TAG\n\n"
      JSON_BODY="$JSON_BODY #### Gradle\n\n"
                  JSON_BODY="$JSON_BODY * implementation 'com.kaltura.netkit:netkit-core"
      JSON_BODY="$NEW_VERSION"
      JSON_BODY="$JSON_BODY'"


cat << EOF > ./post.json
{
      "name": "$NEW_TAG",
      "body": "## Changes from [$PREV_TAG](https://github.com/kaltura/$REPO_NAME/releases/tag/$PREV_TAG)\n\n$JSON_BODY",
      "tag_name": "$NEW_TAG",
      "target_commitish": "$BRANCH_NAME"
}
EOF
    fi

    cat post.json

    curl --request POST \
         --url https://api.github.com/repos/kaltura/"$REPO_NAME"/releases \
         --header "authorization: Bearer $TOKEN" \
         --header 'content-type: application/json' \
         -d@post.json

    rm ./post.json

    # delete temp branch
    #git push origin --delete $BRANCH_NAME
}

notify_teams() {

color=0072C6
  curl "$TEAMS_WEBHOOK" -d @- << EOF
  {
      "@context": "https://schema.org/extensions",
      "@type": "MessageCard",
      "themeColor": "$color",
      "title": "$REPO_NAME $NEW_VERSION",
      "text": "🎉 Release Ready",
      "sections": [
          {
              "facts": [
                  {
                      "name": "Tag",
                      "value": "$NEW_TAG"
                  },
                  {
                      "name": "Version",
                      "value": "$NEW_VERSION"
                  },
                  {
                      "name": "Gradle line",
                      "value": "TBD"
                  }
              ]
          }
      ],
      "potentialAction": [
          {
              "@type": "OpenUri",
              "name": "GitHub Release Page",
              "targets": [
                  {
                      "os": "default",
                      "uri": "$RELEASE_URL"
                  }
              ]
          }
      ]
  }
EOF

}

  RELEASE_TYPE=$RELEASE_TYPE

  REPO_NAME=$REPO_NAME
  MODULE_NAME=$MODULE_NAME
  VERSION_FILE=$MODULE_NAME/version.gradle

  REPO_URL=https://github.com/kaltura/$REPO_NAME
  NEW_VERSION=$NEW_VERSION
  PREV_VERSION=$PREV_VERSION
  TOKEN=$TOKEN
  TEAMS_WEBHOOK=$TEAMS_WEBHOOK

  NEW_TAG=v$NEW_VERSION
  PREV_TAG=v$PREV_VERSION
  RELEASE_URL=$REPO_URL/releases/tag/$NEW_TAG

  if [[ "$RELEASE_TYPE" = "Full" || "$RELEASE_TYPE" = "Update" ]]; then
  BRANCH_NAME="release/$NEW_TAG"
  fi

  if [ "$RELEASE_TYPE" = "Patch" ]; then
  BRANCH_NAME="patch/$NEW_TAG"
  fi

  checkout
  set_version
  build
  release_and_tag
  notify_teams
