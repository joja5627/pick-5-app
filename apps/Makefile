#!/usr/bin/env make

DIRECTORIES := $(foreach dir,$(shell find . -mindepth 1 -maxdepth 1 -type d),$(shell basename $(dir)))

.PHONY: $(DIRECTORIES)

list:
	@echo $(DIRECTORIES)

$(DIRECTORIES):
	cd $(@) && ./gradlew bootRun

# make
