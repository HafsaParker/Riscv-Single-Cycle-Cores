package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class regfileTest extends FreeSpec with ChiselScalatestTester{
    "regfile Test" in{
        test(new RegFile()){c=>
        c.io.raddr1.poke(3.U)
        c.io.raddr2.poke(2.U)
        c.io.wen.poke(true.B)
        c.io.wdata.poke(5.S)
        c.io.waddr.poke(5.U)
        c.clock.step(2)
        

        }
    }
}