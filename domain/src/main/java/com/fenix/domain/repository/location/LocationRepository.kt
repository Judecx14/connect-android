package com.fenix.domain.repository.location

import com.fenix.domain.model.resource.FailureReason
import com.fenix.domain.model.resource.Resource
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
   fun currentLocation() : Flow<Resource<String, FailureReason>>
}