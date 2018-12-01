#!/bin/sh
repo_name=$1
github_username='joja5627'
test -z $repo_name && echo "Repo name required." 1>&2 && exit 1
curl -u "$github_username" https://api.github.com/user/repos -d "{\"name\":\"$repo_name\"}"
git init
git remote add origin "https://github.com/$github_username/$repo_name.git"
