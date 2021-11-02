package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class jalrTest extends FreeSpec with ChiselScalatestTester{
    "jalr Test" in {
        test(new jalr()){c=>
        c.io.x0.poke(10.S)
        c.io.Typee.poke(5.S)
        c.clock.step(20)
        c.io.OutPut.expect(15.S)
        

        }
    }

}