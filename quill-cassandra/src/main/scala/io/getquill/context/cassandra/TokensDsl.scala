package io.getquill.context.cassandra

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.metadata.TokenMap

trait TokensDsl {
  this: CassandraContext[_] =>

  //trait CassandraToken

  case class CassandraToken(construct: CqlSession => TokenMap)

  def tokenLong = quote {
    (a: Long) =>
      infix"$a"
  }

  def token1 = quote {
    (a: Any) =>
      infix"token($a)".as[CassandraToken]
  }

  //  def token2 = quote {
  //    (a: Any, b: Any) =>
  //      infix"token($a, $b)".as[CassandraToken]
  //  }

  implicit class CassandraTokenQuotedExt(token: CassandraToken) {
    //    def le(otherToken: CassandraToken) = quote { infix"$token <= $otherToken".pure.as[Boolean] }
    //    def >=(otherToken: CassandraToken) = quote { infix"$token >= $otherToken".pure.as[Boolean] }
    //    def <(otherToken: CassandraToken) = quote { infix"$token < $otherToken".pure.as[Boolean] }
    //    def >(otherToken: CassandraToken) = quote { infix"$token > $otherToken".pure.as[Boolean] }

    def <=(other: Long) = quote { infix"$token <= $other".pure.as[Boolean] }
    def >=(other: Long) = quote { infix"$token >= $other".pure.as[Boolean] }
    def >(other: Long) = quote { infix"$token > $other".pure.as[Boolean] }
    def <(other: Long) = quote { infix"$token < $other".pure.as[Boolean] }
  }

}
