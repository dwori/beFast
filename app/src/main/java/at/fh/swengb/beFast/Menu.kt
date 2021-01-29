package at.fh.swengb.beFast

object Menu {
    fun consume(f: () -> Unit) = f().let { true }
}