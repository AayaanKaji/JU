# Variables
SRC_DIR="/home/AayaanKaji/ProgramFiles/apache-tomcat-9.0.102/webapps/ROOT"
LIB_DIR="$(SRC_DIR)/WEB-INF/lib"
WEBINF_CLASSES_DIR="$(SRC_DIR)/WEB-INF/classes"
JAVAC=javac

# Main target: Compile Java files into WEB-INF/classes
all: compile

# Compile all .java files in the src directory and subdirectories
compile:
	@mkdir -p "$(WEBINF_CLASSES_DIR)"
	$(JAVAC) -cp "$(LIB_DIR)/*" -d "$(WEBINF_CLASSES_DIR)" -Xlint:unchecked -sourcepath "$(SRC_DIR)" $(shell find "$(SRC_DIR)" -type f -path "$(SRC_DIR)/*/*.java")

# Clean up compiled .class files
clean:
	rm -r $(WEBINF_CLASSES_DIR)

.PHONY: all compile clean
