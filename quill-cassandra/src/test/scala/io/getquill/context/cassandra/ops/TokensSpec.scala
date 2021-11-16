package io.getquill.context.cassandra.ops

import io.getquill._
import io.getquill.context.cassandra.mirrorContext

import java.math.BigInteger

class TokensSpec extends Spec {

  import mirrorContext._
  import io.getquill.context.cassandra._

  "query" - {
    "single PK as token" in {
      val q = quote {
        query[TestEntity].filter(t => token1(t.i) <= (10L))
      }
      mirrorContext.run(q).string mustEqual
        "SELECT s, i, l, o, b FROM TestEntity WHERE i > 10 ALLOW FILTERING"
    }
  }

}
