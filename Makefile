# project settings 
CXX := g++
CXXFLAG :=

SRC_DIR := src
BIN_DIR := bin
BUILD := build 
INC_DIR := include

TARGET := $(BIN_DIR)/amazon

dirs:
	@mkdir -p $(BUILD) $(BIN_DIR)
