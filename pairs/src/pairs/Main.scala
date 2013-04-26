package pairs

import scala.util.Random
import scala.collection.mutable.ListBuffer

object Main {
	def main(args: Array[String]) {
	  val lines = scala.io.Source.fromFile(args(0), "utf-8").getLines
	  val shuffled = Random.shuffle(lines)
	  
	  def printPairs = (a: Pair) => {
		  Thread.sleep(1000)
		  println()
		  print(a.dev1)
		  Thread.sleep(1000)
		  (1 to 3) map { i =>
		    print(".")
		    Thread.sleep(1000)
		    null
		  }
		  print(if(a.dev2==null) "Gibby" else a.dev2)
	  } 
	  
	  val paired = assignPairs(shuffled)
	  paired.foreach(printPairs)
	  println(" (BUGS)")
	}
	
	def assignPairs(devs: Iterator[String]) : List[Pair] = {
	  def op(dev1: String, dev2: String, devs: Iterator[String], pairs: ListBuffer[Pair]) : ListBuffer[Pair] = {
	    if (devs.hasNext == false) {
	      pairs.append(new Pair(dev1, dev2))
	      pairs
	    }
	    else if (dev2 != null) {
	      pairs.append(new Pair(dev1, dev2))
	      op(devs.next, null, devs, pairs); 
	    }
	    else op(devs.next(), dev1, devs, pairs)
	  }
	  
	  op(devs.next, null, devs, ListBuffer()).toList
	}
}

class Pair (val dev1: String, val dev2: String) {}