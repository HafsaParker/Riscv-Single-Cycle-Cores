package ALU
import org.scalatest._
import chiseltest._
import chisel3._
class alucontrolTest extends FreeSpec with ChiselScalatestTester{
    "alucontrol Test" in{
        test(new AluControl()){c=>
        c.io.input_opcode.poke("b001".U)
        c.io.input_func3.poke("b111".U)
        c.io.input_func7.poke(0.B)
        c.clock.step(20)
        c.io.output_control.expect("b0111".U)


        }
    }
    

}