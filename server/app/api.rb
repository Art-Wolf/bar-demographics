module Bar
  class API < Grape::API
    prefix 'api'
    format :json
    mount ::Bar::Stat
    add_swagger_documentation api_version: 'v1'
  end
end
