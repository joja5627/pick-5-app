#!/usr/bin/env make

## Variables

.PHONY: version docs

version:
	@cat version.txt
# version

docs:
	@docker run --rm -it -p 8000:8000 -v $(PWD)/docs:/docs squidfunk/mkdocs-material
	@echo "Documentation running on http://localhost:8000"
# docs

# Makefile
