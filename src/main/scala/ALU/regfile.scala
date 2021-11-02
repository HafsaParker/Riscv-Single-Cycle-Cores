package ALU
import chisel3 . _
class RegFileIO extends Bundle with Config {
val raddr1 = Input(datatype(r_adresswidth.W))
val raddr2 =Input (datatype(r_adresswidth.W))
val rdata1 =Output(SInt(WLEN.W))
val rdata2 =Output(SInt (WLEN.W))
val wen=Input (Bool()) //conrol k reg file se input ayega yaha
val waddr =Input(datatype(r_adresswidth.W)) //rdsel
val wdata =Input (SInt(WLEN.W)) 
}
class RegFile extends Module with Config {
    val io = IO ( new RegFileIO )
    val regs = Reg (Vec(REGFILE_LEN,SInt(WLEN.W )))

    regs(0) := 0.S
    io.rdata1 := regs(io.raddr1)
    io.rdata2 := regs(io.raddr2)
    when(io.wen) {
        when(io.waddr === 0.U){
            regs(io.waddr) := 0.S
        }.otherwise {
            regs(io.waddr) := io.wdata
        }
    }
}

