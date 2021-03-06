package org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.datafactory;

import org.tnmk.practicetransactionoutbox.pro00mysqlsimple.samplebusiness.entity.SampleEntity;

public class SampleEntityFactory {
  public static SampleEntity random() {
    return withName("Sample_" + System.nanoTime());
  }

  public static SampleEntity withName(String name) {
    SampleEntity sampleEntity = new SampleEntity();
    sampleEntity.setName(name);
    return sampleEntity;
  }
}
