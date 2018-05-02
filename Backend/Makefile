
INCLUDE := include
SOURCE := src
#OBJECT := $(SOURCE:.c=.o)
BIN := bin

CPP := gcc-7

CPP_FLAGS := -D_DEBUG -MMD -MP -Werror -Wall -g -pedantic -O0 -std=c++17 -I$(INCLUDE) -I$(INCLUDE)/lib -include General.hpp

LD_FLAGS := -lstdc++ -lm

CFILES := $(shell find $(SOURCE) -name "*.cpp" ! -path "$(SOURCE)/Android/*")

OFILES := $(patsubst $(SOURCE)/%.cpp,$(BIN)/%.o, $(CFILES))
HFILES := $(wildcard $(INCLUDE)/*.hpp)

all: prebuild jurpizza

prebuild:
	@echo "-----------------------------------------------------"
	@echo "                      Building"
	@echo "-----------------------------------------------------"
	@echo $(CFILES)

$(BIN)/%.o: $(SOURCE)/%.cpp
	@-mkdir -p $(@D)
	$(CPP) $(CPP_FLAGS) -o $@ -c $<

jurpizza: $(OFILES)
	$(CPP) $(CPP_FLAGS) -o $@ $^ $(LD_FLAGS)

-include $(OFILES:.o=.d)

.PHONY: clean all
clean:
	rm -rf ./$(BIN)/* jurpizza
