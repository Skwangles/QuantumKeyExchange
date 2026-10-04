# QuantumKeyExchange

A small Java project that simulates a BB84-style quantum key exchange workflow and then uses the agreed bitstring as a key for XOR-based message encryption/decryption.

## Current features

- Simulates transmitter and receiver qubit measurements with random basis selection (`src/Main.java`, `src/Qubit.java`)
- Derives shared keys by comparing exchanged polarizations (`src/Main.java`)
- Demonstrates binary XOR encryption/decryption using the derived key (`src/XORciphering.java`)
- Includes JUnit 4 tests for qubit behavior, key agreement flow, and XOR behavior (`src/*Test.java`)

## Technology stack

- Java (project metadata targets JDK 11 in `.idea/misc.xml`)
- JUnit 4 test code (`org.junit` imports in `src/MainTest.java`, `src/QubitTest.java`, `src/XORtest.java`)

## Repository structure

```text
.
├── src/
│   ├── Main.java
│   ├── Qubit.java
│   ├── XORciphering.java
│   ├── MainTest.java
│   ├── QubitTest.java
│   └── XORtest.java
├── QuantumKeyExchange.iml
└── .idea/
```

## Prerequisites

- Java JDK 11 (or compatible Java version that can compile and run this code)
- For running tests outside an IDE: JUnit 4 and Hamcrest jars available locally

## Build and run

From the repository root:

```bash
mkdir -p out
javac -d out src/Main.java src/Qubit.java src/XORciphering.java
java -cp out Main
```

Expected output includes:

- Original message
- XOR-encrypted message
- Decrypted message
- Whether transmitter and receiver shared keys match

## Testing

The repository does not include Maven/Gradle wrappers; tests are plain JUnit 4 classes in `src/`.

If you have JUnit 4 + Hamcrest jars available, you can run:

```bash
mkdir -p out
javac -d out -cp "<path-to-junit4.jar>:<path-to-hamcrest-core.jar>" src/*.java
java -cp "out:<path-to-junit4.jar>:<path-to-hamcrest-core.jar>" org.junit.runner.JUnitCore MainTest QubitTest XORtest
```

You can also run the tests directly from an IDE using the existing project/module files.

## Configuration

There are no external configuration files or environment variables. Runtime behavior currently depends on:

- Random basis/value generation via `Math.random()` in `Main` and `Qubit`
- The hardcoded qubit count (`1024`) and demo secret message in `Main.main`

## Limitations and current status

- This is an educational simulation, not a production cryptographic implementation.
- Results vary between runs because of random measurement and basis selection.
- The demo cipher expects binary strings (`0`/`1`) and throws on non-binary input (`XORciphering.xor`).
- The project is currently IDE-centric (IntelliJ metadata is present; no build tool configuration is included).

## License and attribution

No `LICENSE` file is currently present in this repository. Add one if you intend to publish reuse terms.

