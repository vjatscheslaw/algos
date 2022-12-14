// MODIFIED BY VIACHESLAV MIKHAILOV
// From Classic Computer Science Problems in Java
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package algos.dna;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class GeneUtil {

    public static Gene generateRandomGene() {
        ThreadLocalRandom rndm = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11_111_111; i++)
            appendRandomCodonText(rndm, sb);
//            sb.append('A');
        return new Gene(sb.toString());

    }

    public static Gene.Codon generateRandomCodon() {
        ThreadLocalRandom rndm = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder();
        appendRandomCodonText(rndm, sb);
        return new Gene.Codon(sb.toString());
    }

    public static void appendRandomCodonText(ThreadLocalRandom rndm, StringBuilder sb) {
        for (int i = 0; i < 3; i++) {
            int letter = rndm.nextInt(0, 4);
            switch (letter) {
                case 0:
                    sb.append('A');
                    break;
                case 1:
                    sb.append('C');
                    break;
                case 2:
                    sb.append('G');
                    break;
                case 3:
                    sb.append('T');
                    break;
                default:
                    break;
            }
        }
    }

    public static <T extends Gene.Codon> boolean linearContains(List<T> gene, T codon) {
        for (int i = 0; i < gene.size(); i++) if (gene.get(i).compareTo(codon) == 0) return true;
        return false;
    }

    public static <T extends Gene.Codon> boolean binaryContains(List<T> sortedCodons, T key) {
        int low = 0;
        int high = sortedCodons.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int comparison = sortedCodons.get(middle).compareTo(key);
            if (comparison < 0) {
                low = middle + 1;
            } else if (comparison > 0) {
                high = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
