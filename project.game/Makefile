# Variables
SRC_DIR := src
BIN_DIR := bin
LIBS := $(shell find . -maxdepth 1 -name '*.jar' -printf '%p:')
CLASSPATH := $(LIBS)

# Compilateur et options
JAVAC := javac
JAVA := java
JAVAC_FLAGS := -d $(BIN_DIR) -classpath $(CLASSPATH) --release 11

# Récupérer tous les fichiers source .java
SRC_FILES := $(shell find $(SRC_DIR) -name '*.java')

# Cibles
all: $(BIN_DIR)/classes

# Règle pour compiler les fichiers .java en .class
$(BIN_DIR)/classes: $(SRC_FILES)
	@mkdir -p $(BIN_DIR)
	$(JAVAC) $(JAVAC_FLAGS) $(SRC_FILES)

# Cible de nettoyage
clean:
	rm -rf $(BIN_DIR)

# Cible de test (optionnelle)
run: all
	$(JAVA) -cp $(BIN_DIR):$(CLASSPATH) src/Game  

.PHONY: all clean run
