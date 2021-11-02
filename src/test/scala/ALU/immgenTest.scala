package ALU
import chisel3 . _
import chisel3 . util
import org . scalatest . _
import chiseltest . _
class immgenTest extends FreeSpec with ChiselScalatestTester{
  "immgen Test"in {
    test(new ImmediateGen(UInt(32.W),SInt(32.W))){c=>
    c.io.instr.poke("h00210663".U)
    c.io.pc.poke(4.U)
    c.clock.step(20)
    c.io.immd_se.expect(0.U)
    c.io.branch_se.expect(16.U)
    c.io.j_sel.expect(0.U)

    }


  }
}

// import chiseltest . experimental . TestOptionBuilder . _
// import chiseltest . internal . VerilatorBackendAnnotation
// import scala . util . Random
// class ImmediateGenTest extends FreeSpec with ChiselScalatestTester {
// "ImmediateGen Test" in {
// test(new ImmediateGen(UInt(32.W),SInt(32.W))) { c =>
// val imm_arr = Array("h00800513","h00B02623","h014000EF","h00058863","h000065B7") //,"h00B02623","h014000EF","h00058863","h000041B7")
// for ( i <- 0 until 100) {
//   val in = Random . nextInt (3)
//   //println("***in**",in)
//   val inst = imm_arr(in)
//   val result = inst match {
//     case("h00800513") => 8  //Itype
//      case("h00B02623") => 12 //Stype
//     case("h014000EF") => 20 //J Type
//     case("h00058863") => 16 //B Type
//     case("h000065B7") => 6 //U Type
//     case _ => 0
// }
// println("***result***", result)
// c . io . instr . poke ( inst . U )
// c.io.pc.poke(4.U)
// c.clock.step (1)
// c.io.immd_se.expect(result.U)
// c.io.branch_se.expect()
// }
// c . clock . step (2)
// }
// }
// }