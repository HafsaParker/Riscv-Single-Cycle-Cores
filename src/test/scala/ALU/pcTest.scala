package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class pcTest extends FreeSpec with ChiselScalatestTester{
    "pc Test" in {
        test(new pc()){c=>
        c.io.inpuT.poke(4.S)
        c.clock.step(20)
        c.io.out.expect(4.S)
        c.io.pc_plus4.expect(8.S)

        }
    }
}