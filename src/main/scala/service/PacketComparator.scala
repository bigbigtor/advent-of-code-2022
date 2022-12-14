package service

import com.fasterxml.jackson.databind.node.{ArrayNode, IntNode}
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}

class PacketComparator {

  private val mapper = ObjectMapper()

  def compare(left: JsonNode, right: JsonNode): Int =
    (left, right) match
      case (l: ArrayNode, r: ArrayNode) => compareLists(l, r)
      case (l: IntNode, r: ArrayNode) => compareLists(getList(l), r)
      case (l: ArrayNode, r: IntNode) => compareLists(l, getList(r))
      case (l: IntNode, r: IntNode) => l.intValue().compare(r.intValue())

  private def compareLists(l: ArrayNode, r: ArrayNode): Int =
    (0 until l.size.min(r.size))
      .map(i => compare(l.get(i), r.get(i)))
      .find(res => res != 0)
      .getOrElse(l.size.compare(r.size))

  private def getList(value: IntNode): ArrayNode =
    val arrayNode = mapper.createArrayNode()
    arrayNode.add(value)
    arrayNode
}
