class SistemaInterno {

    fun entra(admin: Autenticavel, senha: Int){
        if(admin.autentica(senha)){
            println("Bem vindo ao KotlinBank")
        } else {
            println("Falha na autenticação")
        }
    }

}