#!/bin/sh
#

PROJECT_NAME=bedims-uitest

CURRENT_DIR=`pwd`
INSTALL_DIR_ROOT=/opt/bedims-uitest
INSTALL_DIR_NAME=release-`date +%Y%m%d-%H%M%S`
PREVIOUS_LATEST=

# Build
mvn clean package
if [ $? -ne 0 ];
then
    exit $?
fi

# Create install dir if not exists
if [ ! -d $INSTALL_DIR_ROOT ];
then
    sudo mkdir $INSTALL_DIR_ROOT
fi

# Change into Install Dir
cd $INSTALL_DIR_ROOT

# Download jetty-runner.jar
if [ ! -f jetty-runner.jar ];
then
    sudo wget http://repo2.maven.org/maven2/org/mortbay/jetty/jetty-runner/8.1.9.v20130131/jetty-runner-8.1.9.v20130131.jar
    sudo ln -s jetty-runner-8.1.9.v20130131.jar jetty-runner.jar
fi

# Install into unique dir encoded by timestamp
sudo mkdir $INSTALL_DIR_NAME
sudo cp $CURRENT_DIR/target/bedims-uitest.war $INSTALL_DIR_NAME
if [ -L $INSTALL_DIR_ROOT/latest ];
then
    sudo stop $PROJECT_NAME
    sleep 15s
    PREVIOUS_LATEST=`readlink latest`
    sudo rm -f $INSTALL_DIR_ROOT/latest
    sudo rm /etc/init/$PROJECT_NAME.conf
fi

# Link to the latest installation
sudo ln -s $INSTALL_DIR_NAME latest

# Change back to starting dir
cd $CURRENT_DIR

# Remove old upstart file if it is diff
if [ -f /etc/init/$PROJECT_NAME.conf ];
then
  diff /etc/init/$PROJECT_NAME.conf setup/etc/init/$PROJECT_NAME.conf >/dev/
  if [ $? -ne 0 ];
  then
    sudo rm /etc/init/$PROJECT_NAME.conf
  fi
fi

# Install upstart file
if [ ! -f /etc/init/$PROJECT_NAME.conf ];
then
  sudo cp setup/etc/init/$PROJECT_NAME.conf /etc/init/$PROJECT_NAME.conf
fi

# Start server
sudo start $PROJECT_NAME
sleep 15s

# Delete previous
cd $INSTALL_DIR_ROOT
if [ "$PREVIOUS_LATEST" != "" ];
then
    sudo rm -r -f $PREVIOUS_LATEST
fi
cd $CURRENT_DIR
