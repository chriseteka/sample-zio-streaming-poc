package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.implementations.FamilyServiceImpl
import ice.chrisworks.naive.external.service.models.Family
import zio.ZLayer

trait FamilyService extends BaseCRUDService[Family] {
  //More Unimplemented Func here
}

object FamilyService extends BaseCRUDServiceAccessor[FamilyService, Family] {

  object Layers {
    val live = ZLayer.succeed(FamilyServiceImpl())
  }

  //Write Accessors for the Extra Func Here
}
