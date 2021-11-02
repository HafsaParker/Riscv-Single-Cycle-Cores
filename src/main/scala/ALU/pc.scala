package ALU
import chisel3._
import chisel3.util._
// trait cOnfig{
//     val WLEN = 32
// }
//(widthpc:Int)
class pcIO extends Bundle with Config{
    val inpuT = Input(SInt(WLEN.W))
    val out = Output(SInt(WLEN.W))
    val pc_plus4 =Output(SInt(WLEN.W)) 
}
//(W_pc:Int)
class pc extends Module with Config{
    val io = IO(new pcIO())
    val register  = RegInit(0.S(WLEN.W))
    register:=io.inpuT
    io.out:= register
    io.pc_plus4:=register+4.S


}