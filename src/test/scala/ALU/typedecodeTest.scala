package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class typedecodeTest extends FreeSpec with ChiselScalatestTester{
    "type decode test" in{
        test(new TypeDecode()){c=>
        c.io.opcode.poke("b0010111".U)
        //c.io.branch_alu_out.poke(1.B)
        c.clock.step(20)
        c.io.iType.expect(0.B)
        c.io.rType.expect(0.B)
        c.io.load.expect(0.B)
        c.io.store.expect(0.B)
        c.io.bType.expect(0.B)
        c.io.jal.expect(0.B)
        c.io.jalr.expect(0.B)
        c.io.lui.expect(0.B)
        c.io.auipc.expect(1.B)
        //c.io.branch_true.expect(1.B)


        }

    }
}