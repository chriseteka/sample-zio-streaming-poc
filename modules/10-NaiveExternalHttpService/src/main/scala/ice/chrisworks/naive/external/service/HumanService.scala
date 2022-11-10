package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.implementations.HumanServiceImpl
import ice.chrisworks.naive.external.service.models.Human
import zio.ZLayer

trait HumanService extends BaseCRUDService[Human] {
  //More Unimplemented Func here
}

object HumanService extends BaseCRUDServiceAccessor[HumanService, Human] {

  object Layers {
    val live = ZLayer.fromFunction(HumanServiceImpl(_))
  }

  //Write Accessors for the Extra Func Here
}
