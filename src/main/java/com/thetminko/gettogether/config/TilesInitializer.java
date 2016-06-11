package com.thetminko.gettogether.config;

import org.apache.tiles.factory.AbstractTilesContainerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;

/**
 * Created by developer on 11/6/16.
 */
public class TilesInitializer extends AbstractTilesInitializer {
  @Override
  protected AbstractTilesContainerFactory createContainerFactory(
      ApplicationContext applicationContext) {
    return new TilesContainerFactory();
  }
}
