package com.example.base

interface UseCase<IN : Any, OUT : Any> {
    suspend operator fun invoke(input: IN): OUT
}

suspend operator fun <OUT : Any> UseCase<Unit, OUT>.invoke(): OUT = invoke(Unit)