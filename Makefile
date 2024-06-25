JAVAC = javac
JAVA = java
SRC_DIRS =  src src/AI src/Action src/Condition src/Model src/Model/Collision\
			src/Model/Entities src/Model/World src/View src/View/Avatars
BIN_DIR = bin
LIB_DIR = 
LIBS = project.game/gson-2.11.0.jar:project.game/info3.game.jar:project.game/parser.jar

SOURCES := $(wildcard $(addsuffix /*.java,project.game/$(SRC_DIRS)))

CLASSES := $(SOURCES:%.java=$(BIN_DIR)/%.class)

all: $(CLASSES)

$(BIN_DIR)/%.class: %.java
	@mkdir -p $(BIN_DIR)/$(dir $<)
	$(JAVAC) -d $(BIN_DIR) -cp $(LIBS) $<

clean:
	rm -rf $(BIN_DIR)/*

run: all
	$(JAVA) -cp $(BIN_DIR):$(LIBS) MainClass

.PHONY: all clean run
