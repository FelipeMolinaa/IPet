package com.proudpet.ipet.Views;

import android.content.Context;
import android.widget.ListView;

import com.proudpet.ipet.adapters.ListaNoticiasAdapter;
import com.proudpet.ipet.classes.Noticia;

public class ListaNoticiasView {
    private final ListaNoticiasAdapter adapter;
    private final Context context;

    public ListaNoticiasView(Context context) {
        this.context = context;
        this.adapter = new ListaNoticiasAdapter(this.context);
        AdicionaNoticias();
    }

    private void AdicionaNoticias() {
        adapter.adicionaNoticia(new Noticia("31","Outubro","2019","1ª Cãosciência da zona sul reuniu mais de 300 pessoas em Interlagos junto com seus pets de estimação\n","https://www.terra.com.br/noticias/dino/1-caosciencia-da-zona-sul-reuniu-mais-de-300-pessoas-em-interlagos-junto-com-seus-pets-de-estimacao,1c7e87f1b5d4de83d7b86d8e45dc3d5fvrxl7lj5.html","Terra"));
        adapter.adicionaNoticia(new Noticia("29","Outubro","2019","Casos de raiva animal são confirmados em Itaberá neste ano","https://g1.globo.com/sp/itapetininga-regiao/noticia/2019/10/29/casos-de-raiva-animal-sao-confirmados-em-itabera-neste-ano.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("28","Outubro","2019","No primeiro dia de vacinação contra raiva animal, Jaguariúna imuniza 20% dos Cães e 22% dos gatos","https://oregional.net/no-primeiro-dia-de-vacinacao-contra-raiva-animal-jaguariuna-imuniza-20-dos-caes-e-22-dos-gatos-100054","Regional"));
        adapter.adicionaNoticia(new Noticia("23","Outubro","2019","Por falta de vacinas, campanha antirrábica é encerrada em Manaus","https://g1.globo.com/am/amazonas/noticia/2019/10/23/por-falta-de-vacinas-campanha-antirrabica-e-encerrada-em-manaus.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("12","Outubro","2019","Vacinação antirrábica: Piracicaba abre 13 locais e espera imunizar 3,9 mil animais neste sábado","https://g1.globo.com/sp/piracicaba-regiao/noticia/2019/10/12/vacinacao-antirrabica-piracicaba-abre-13-locais-e-espera-imunizar-39-mil-animais-neste-sabado.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("02","Outubro","2019","Doses de vacina antirrábica recebidas foram insuficientes em Varginha, MG","https://g1.globo.com/mg/sul-de-minas/noticia/2019/10/02/doses-de-vacina-antirrabica-recebidas-foram-insuficiente-em-varginha-mg.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("29","Setembro","2019","Com 10 casos de raiva, Piracicaba faz segunda etapa de vacinação antirrábica neste sábado","https://g1.globo.com/sp/piracicaba-regiao/noticia/2019/09/28/com-10-casos-de-raiva-piracicaba-faz-segunda-etapa-de-vacinacao-antirrabica-neste-sabado.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("27","Setembro","2019","Deixar de vacinar animais domésticos é crime, alerta Delegacia do Meio Ambiente em Manaus","https://g1.globo.com/am/amazonas/noticia/2019/09/27/deixar-de-vacinar-animais-domesticos-e-crime-alerta-delegacia-do-meio-ambiente-em-manaus.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("25","Setembro","2019","Dia D da vacinação contra raiva em animais acontece no sábado (28) no RN","https://g1.globo.com/rn/rio-grande-do-norte/noticia/2019/09/25/dia-d-da-vacinacao-contra-raiva-em-animais-acontece-no-sabado-28-no-rn.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("20","Setembro","2019","Campanha de vacinação contra a raiva em cães e gatos termina neste sábado no DF","https://g1.globo.com/df/distrito-federal/noticia/2019/09/20/campanha-de-vacinacao-contra-a-raiva-em-caes-e-gatos-termina-neste-sabado-no-df.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("19","Setembro","2019","Em Rio Branco, 25 mil cães e gatos ainda precisam ser vacinados contra raiva","https://g1.globo.com/ac/acre/noticia/2019/09/18/em-rio-branco-25-mil-caes-e-gatos-ainda-precisam-ser-vacinados-contra-raiva.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("19","Setembro","2019","Campanha Antirrábica Animal mantém cinco postos fixos de vacinação em Manaus; veja endereços","https://g1.globo.com/am/amazonas/noticia/2019/09/18/campanha-antirrabica-animal-mantem-cinco-postos-fixos-de-vacinacao-em-manaus-veja-enderecos.ghtml","G1"));
        adapter.adicionaNoticia(new Noticia("16","Setembro","2019","Cães e gatos: conheça as doenças que mais afetam os pets","https://catracalivre.com.br/parceiros-catraca/carrefour/caes-e-gatos-conheca-as-doencas-que-mais-afetam-os-pets/","Catraca Livre"));
        adapter.adicionaNoticia(new Noticia("14","Setembro","2019","Após registros de raiva em morcegos, Piracicaba mantém campanha antirrábica em setembro","https://g1.globo.com/sp/piracicaba-regiao/noticia/2019/09/14/apos-registros-de-raiva-em-morcegos-piracicaba-mantem-campanha-antirrabica-em-setembro.ghtml","G1"));
    }

    public void configuraAdapter(ListView listaNoticias) {
        listaNoticias.setAdapter(adapter);
    }
}
