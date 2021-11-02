package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class topfiletest extends FreeSpec with ChiselScalatestTester{
    "top file test" in {
        test(new topfile()){c=>
        c.clock.step(900)
        


        }
    }
}