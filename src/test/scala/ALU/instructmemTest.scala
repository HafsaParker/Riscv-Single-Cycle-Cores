package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class instructmemTest extends FreeSpec with ChiselScalatestTester{
    "instructmem Test" in{
        test(new instructionmem()){c=>
        c.io.addr.poke(1.U)
        c.clock.step(4)
        //c.io.inst.expect("h00200513".U)

        }
    }
}