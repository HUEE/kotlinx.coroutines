/*
 * Copyright 2016-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */
@file:JvmName("SegmentQueueSynchronizerJvm")

package kotlinx.coroutines.internal

import java.util.concurrent.locks.*

internal actual fun resumeWaiter(waiter: Any) {
    waiter as? Thread ?: error("Unexpected waiter type")
    LockSupport.unpark(waiter)
}

internal actual fun suspendWaiter(waiter: Any): Unit = LockSupport.park()

internal fun SegmentQueueSynchronizer<Unit>.suspendCurrentThread(): Boolean =
    suspendBlocking(Thread.currentThread()) != null

internal fun <T : Any> SegmentQueueSynchronizer<T>.suspendCurrentThread(): T? =
    suspendBlocking(Thread.currentThread())

