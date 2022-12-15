package domain

enum CaveElement(repr: Char):
  case Rock extends CaveElement('#')
  case Air extends CaveElement('.')
  case Sand extends CaveElement('o')
  case SandSource extends CaveElement('+')

  override def toString: String = this.repr.toString