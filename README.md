# Bowler SBT Quickstart
The Bowler Quickstart is a Skeleton sbt project and Bowler app that gets you up and running with a minimal Bowler app that you can adapt in no time.

## Setup Instructions
* Have [Simple Build Tool ("sbt")](http://code.google.com/p/simple-build-tool/) installed
* Create the directory where you want to start your app and run "git init"
* Do  "git pull https://github.com/wfaler/bowler-quickstart.git"
* Run sbt
* Done! Ready to start writing your Bowler app!

To deploy to heroku install the heroku gem. And create an account with a cedar stack.

Then do
* git push <your heroku repo> master
* Watch in joy!
 

A word of advice, and probably a bug, is that you should not use non ascii characters like øæå in your mustache files. If you do so there will be losts of head scraching and 500's. 