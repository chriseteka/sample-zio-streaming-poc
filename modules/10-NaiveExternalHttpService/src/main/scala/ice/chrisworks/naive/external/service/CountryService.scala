package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.implementations.CountryServiceImpl
import zio.ZLayer

trait CountryService {

}

object CountryService {
  object Layers {
    val live = ZLayer.succeed(CountryServiceImpl())
  }
}
