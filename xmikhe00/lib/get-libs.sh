# wget https://repo1.maven.org/maven2/org/openjfx/javafx-controls/13/javafx-controls-13.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-base/13/javafx-base-13.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-base/13/javafx-base-13-linux.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-controls/13/javafx-controls-13-linux.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-fxml/13/javafx-fxml-13.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-fxml/13/javafx-fxml-13-linux.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-graphics/13/javafx-graphics-13.jar
# wget https://repo1.maven.org/maven2/org/openjfx/javafx-graphics/13/javafx-graphics-13-linux.jar

wget https://repo1.maven.org/maven2/org/openjfx/javafx-controls/18/javafx-controls-18.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-base/18/javafx-base-18.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-base/18/javafx-base-18-linux.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-controls/18/javafx-controls-18-linux.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-fxml/18/javafx-fxml-18.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-fxml/18/javafx-fxml-18-linux.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-graphics/18/javafx-graphics-18.jar
wget https://repo1.maven.org/maven2/org/openjfx/javafx-graphics/18/javafx-graphics-18-linux.jar
wget https://download2.gluonhq.com/openjfx/18/openjfx-18_linux-x64_bin-sdk.zip
unzip openjfx-18_linux-x64_bin-sdk.zip
rm -rf openjfx-18_linux-x64_bin-sdk.zip

# mvn install:install-file\
# -DlocalRepositoryPath=lib\
# -Dfile=lib/javafx-controls-18.jar\
# -DgroupId=org.openjfx\
# -DartifactId=javafx-controls\
# -Dpackaging=jar\
# -Dversion=18
