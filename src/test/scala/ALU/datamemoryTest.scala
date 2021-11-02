package ALU
import chisel3._
import org.scalatest._
import chiseltest._

class DataMemoryTest extends FreeSpec with ChiselScalatestTester { 
    "Data Memory Test" in {  
        test(new DataMemory(new Parameters(32,32))) { c =>
        c.io.data_in.poke(32.S)
        c.io.wr_en.poke(true.B)
        c.io.addr.poke(32.U)
        c.clock.step(10)
        c.io.data_out.expect(0.S)  
        }
    }
}