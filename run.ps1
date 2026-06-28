# run.ps1

# Função auxiliar para buscar o valor do segredo via BWS CLI
function Get-BWSSecret {
    param([string]$secretId)
    $json = bws secret get $secretId | ConvertFrom-Json
    return $json.value
}

# 1. Busca os valores no Bitwarden usando os IDs
# Substitua os "ID_..." pelos IDs reais gerados no seu painel do Bitwarden
$env:DB_HOST_18 = Get-BWSSecret "ID_DO_DB_HOST"
$env:DB18_PORT  = Get-BWSSecret "ID_DO_DB_PORTA"
$env:DB18_NAME  = Get-BWSSecret "ID_DO_DB_NOME"
$env:DB_USER    = Get-BWSSecret "ID_DO_DB_USUARIO"
$env:DB_PASSWORD = Get-BWSSecret "ID_DO_DB_SENHA"

# 2. Inicia o Spring Boot
mvn spring-boot:run