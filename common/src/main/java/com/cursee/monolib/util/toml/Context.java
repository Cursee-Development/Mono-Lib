package com.cursee.monolib.util.toml;

import java.util.concurrent.atomic.AtomicInteger;

public class Context {
  final Identifier identifier;
  final AtomicInteger line;
  final Results.Errors errors;
  
  public Context(Identifier identifier, AtomicInteger line, Results.Errors errors) {
    this.identifier = identifier;
    this.line = line;
    this.errors = errors;
  }

  public Context with(Identifier identifier) {
    return new Context(identifier, line, errors);
  }
}
