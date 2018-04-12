org.springframework.cloud.contract.spec.Contract.make {
    request {
        method GET()

        url("/maps/api/distancematrix/json") {
            queryParameters {
                parameter 'origins': '48.83,8.91' // Weissach
                parameter 'destinations': '1111111.1,2.2' // Not a location, invalid coordinate
            }
        }
    }

    response {
        status 200

        body([
                rows  : [[
                                 elements: [[
                                                    status: 'NOT_FOUND'
                                            ]]
                         ]],
                status: 'OK'
        ])

        headers {
            contentType(applicationJson())
        }
    }
}

