JC = javac
SRC = test
BIN = test

SOURCES := $(wildcard $(SRC)/*.java)
CLASSES := $(SOURCES:$(SRC)/%.java=$(BIN)/%.class)

all: $(CLASSES)

$(BIN)/%.class: $(SRC)/%.java
	$(JC) -d $(BIN) $<

# clean:
# rm -rf $(BIN)/*.class
