package ALU
import chisel3._
import chisel3.util._
import org.scalatest._
import chiseltest._
// // import chiseltest.experimental.TestOptionBuilder._
// // import chiseltest.internal.VerilatorBackendAnnotation

import scala.util.Random
import funct3._

class Task1Test extends FreeSpec with ChiselScalatestTester{
    "Branch ALu Test" in {
        test( new branchAlu ){c => 
        val array_op = Array(BEQ, BNE, BLT, BGE, BLTU, BGEU)

        for (i <- 0 until 100){
            val a = Random.nextLong() & 0xFFFFFFFFL
            val b = Random.nextLong() & 0xFFFFFFFFL
            val branch_pin = Random.nextBoolean() 
            val opr = Random.nextInt(5)
            val func_3 = array_op(opr)


        val result: Bool = func_3 match {
            case BEQ => (a.toInt == b.toInt).B
            case BNE => (a.toInt != b.toInt).B
            case BLT => (a.toInt < b.toInt).B
            case BGE => (a.toInt >= b.toInt).B
            case BLTU => (a < b).B
            case BGEU => (a >= b).B
            case _ => 0.B
        }

        val result1 = if(branch_pin === true) result else 0.B

        c.io.arg_x.poke(a.S)
        c.io.arg_y.poke(b.S)
        //c.io.branch.poke(branch_pin.B)
        c.io.func3.poke(func_3)
        c.clock.step(1)
        c.io.br_taken.expect(result1.asBool)
        }        

        c.clock.step(2)
        }
    }
}