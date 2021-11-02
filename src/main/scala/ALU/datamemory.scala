package ALU
import chisel3._
import chisel3.util._

class Parameters ( dWidth : Int , aWidth : Int ) extends Bundle with Config {
val addrWidth =  datatype( aWidth . W )
val dataWidth = SInt ( dWidth . W )
}
class DataMemory ( params : Parameters ) extends Module {
    val io = IO ( new Bundle {
    val data_in = Input ( params . dataWidth )
    val data_out = Output ( params . dataWidth )
    val addr = Input ( params . addrWidth )
    val wr_en = Input ( Bool () )
})
val memory = Mem (32 , params . dataWidth )
io . data_out := 0. S
when ( io . wr_en ) {
memory . write ( io . addr , io . data_in )
} .otherwise {
io . data_out := memory . read ( io . addr )
}
}