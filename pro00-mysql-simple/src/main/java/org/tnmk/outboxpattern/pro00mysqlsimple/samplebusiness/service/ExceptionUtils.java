package org.tnmk.outboxpattern.pro00mysqlsimple.samplebusiness.service;

public interface ExceptionUtils {
  static void throwAnException() {
    throw new NullPointerException("Some Exception");
  }
}