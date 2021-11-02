package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class ALuTest extends FreeSpec with ChiselScalatestTester{
    "Alu Test" in {
        test(new ALu()){c=>
        c.io.input_a.poke(6.S)
        c.io.input_b.poke(-16.S)
        c.io.sel.poke(0.U)
        c.clock.step(20)
        c.io.out.expect(-10.S)


        }
    }
}
