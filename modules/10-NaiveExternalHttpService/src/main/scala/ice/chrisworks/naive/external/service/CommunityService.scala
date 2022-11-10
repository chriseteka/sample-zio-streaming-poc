package ice.chrisworks.naive.external.service

import ice.chrisworks.naive.external.service.implementations.CommunityServiceImpl
import ice.chrisworks.naive.external.service.models.Community
import zio.ZLayer

trait CommunityService extends BaseCRUDService [Community] {
  //More Unimplemented Func here
}

object CommunityService extends BaseCRUDServiceAccessor[CommunityService, Community] {

  object Layers {
    val live = ZLayer.succeed(CommunityServiceImpl())
  }

  //Write Accessors for the Extra Func Here

}
