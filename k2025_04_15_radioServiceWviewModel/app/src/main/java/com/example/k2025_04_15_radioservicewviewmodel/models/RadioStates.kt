
enum class RadioStates(val stateInteger: Int) {
    SETUP_RADIO(0),
    START(1),
    STOP(2),
    PREPARE_ASYNC(3),
    PAUSE(4),
    DESTROY(5)
}