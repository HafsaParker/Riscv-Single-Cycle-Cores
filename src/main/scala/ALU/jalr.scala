package ALU
import chisel3._
import chisel3.util._

class jalr extends Module with Config{
    val io  = IO(new Bundle{
        val x0 = Input(SInt(WLEN.W))
        val Typee = Input(SInt(WLEN.W))
        val OutPut = Output(SInt(WLEN.W))

    })
    io.OutPut:=io.x0+io.Typee
}
//(work on the output)