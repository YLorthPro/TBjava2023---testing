# Table des Matières

1. [Documentation des Tests en Java](#documentation-des-tests-en-java)
    - [Arrange (Mettre en place)](#arrange-mettre-en-place)
    - [Act (Agir)](#act-agir)
    - [Assert (Vérifier)](#assert-vérifier)
        - [Exemple de Test](#exemple-de-test)

2. [Tests Unitaires](#tests-unitaires)
    - [Définition et Objectif](#définition-et-objectif)
    - [Outils Utilisés](#outils-utilisés)
    - [Exemple de Test Unitaire](#exemple-de-test-unitaire)

3. [Tests d'Intégration](#tests-dintégration)
    - [Définition et Objectif](#définition-et-objectif-1)
    - [Outils Utilisés](#outils-utilisés-1)
    - [Exemple de Test d'Intégration](#exemple-de-test-dintégration)

4. [Pense-Bête Assertions en Java](#pense-bête-assertions-en-java)
    - [Assertions de JUnit](#assertions-de-junit)

5. [Pense-Bête Mockito en Java](#pense-bête-mockito-en-java)
    - [1. Création de Mocks](#1-création-de-mocks)
    - [2. Définition de Comportements](#2-définition-de-comportements)
    - [3. Vérification des Appels](#3-vérification-des-appels)
    - [4. Argument Matchers](#4-argument-matchers)
    - [5. Reset du Mock](#5-reset-du-mock)

6. [Pense-Bête MockMvc en Java](#pense-bête-mockmvc-en-java)
    - [1. Configuration de MockMvc](#1-configuration-de-mockmvc)
    - [2. Envoyer des Requêtes](#2-envoyer-des-requêtes)
    - [3. Assertions de Réponse](#3-assertions-de-réponse)
    - [4. Assertions de Vue (View)](#4-assertions-de-vue-view)
    - [5. Assertions de Redirection](#5-assertions-de-redirection)

7. [Tests d'Endpoints REST](#tests-dendpoints-rest)
    - [Envoyer des Requêtes pour Tester les Endpoints REST](#envoyer-des-requêtes-pour-tester-les-endpoints-rest)
    - [Assertions de Réponse pour les API REST](#assertions-de-réponse-pour-les-api-rest)

# Documentation des Tests en Java

## Arrange (Mettre en place)

Dans cette étape, vous configurez l'environnement de test. Cela implique généralement la création d'objets, l'initialisation de variables et la configuration de tout ce qui est nécessaire pour exécuter le test. L'idée est de préparer le terrain pour le comportement que vous souhaitez tester.

## Act (Agir)

C'est l'étape où vous exécutez l'action ou le comportement que vous testez. Cela peut être l'appel d'une méthode, l'invocation d'une fonction ou tout autre comportement spécifique que vous voulez vérifier. L'objectif est de reproduire le scénario qui doit être testé.

## Assert (Vérifier)

Cette étape consiste à vérifier que le résultat de l'action effectuée dans l'étape "Act" correspond à ce que vous attendiez. Vous comparez le résultat réel avec le résultat attendu, et si la vérification échoue, le test échoue également.

```java
    @Test
    public void testAddition() {
        // Arrange
        CalculatorService calculatorService = new CalculatorService();
        int a = 2;
        int b = 3;
    
        // Act
        int result = calculatorService.add(a, b);
    
        // Assert
        Assertions.assertEquals(5, result);
    }
```
# Tests Unitaires

## Définition et Objectif

Les tests unitaires visent à tester une unité de code isolée, généralement une méthode ou une classe, pour s'assurer qu'elle produit les résultats attendus. Ces tests sont essentiels pour garantir le bon fonctionnement des composants individuels de l'application.

## Outils Utilisés

- **JUnit**: Framework de test unitaire standard pour Java.
- **Mockito**: Bibliothèque de mocking pour faciliter la création de mocks (simulations d'objets) lors des tests.

## Exemple

```java
    @Test
    public void testAddition() {
        // Arrange
        CalculatorService calculatorService = new CalculatorService();
        int a = 2;
        int b = 3;
    
        // Act
        int result = calculatorService.add(a, b);
    
        // Assert
        Assertions.assertEquals(5, result);
    }
```

# Tests d'Intégration

## Définition et Objectif

Les tests d'intégration s'assurent que les différentes parties d'une application interagissent correctement entre elles. L'objectif est de valider que les composants intégrés fonctionnent ensemble conformément aux spécifications.

## Outils Utilisés

- **Spring TestContext Framework**: Fournit des fonctionnalités pour faciliter les tests d'intégration avec Spring.
- Annotations spécifiques telles que @DataJpaTest, @WebMvcTest, etc., sont utilisées pour configurer le contexte de test.

## Exemple

```java
        @SpringBootTest
        class SampleControllerIntegrationTest {
        
            @Autowired
            private MockMvc mockMvc;
        
            @Test
            void testEndpoint() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/api/resource"))
                       .andExpect(status().isOk())
                       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                       .andExpect(jsonPath("$.key").value("expectedValue"));
            }
        }
```

# Pense-Bête Assertions en Java

## Assertions de JUnit

### Vérification d'égalité
```java
        assertEquals(expected, actual);
```
### Vérification de non-nullité
```java
        assertNotNull(object);
```
### Vérification de nullité
```java
        assertNull(object);
```
### Vérification de vrai
```java
        assertTrue(condition);
```
### Vérification de faux
```java
        assertFalse(condition);
```
### Vérification de référence d'objet identique
```java
        assertSame(expected, actual);
```
### Vérification de référence d'objet différente
```java
        assertNotSame(expected, actual);
```
### Vérification de levée d'exception
```java
        assertThrows(ExpectedException.class, () -> methodThatThrowsException());
```
# Pense-Bête Mockito en Java

## 1. Création de Mocks
```java
        MyClass myMock = mock(MyClass.class);
```
## 2. Définition de Comportements

### Retourner une valeur attendue
```java
        when(myMock.method()).thenReturn(expectedValue);
```
### Lancer une exception
```java
        when(myMock.method()).thenThrow(new MyException());
```
### Répondre de manière personnalisée
```java
        when(myMock.method()).thenAnswer(invocation -> customAnswer(invocation));
```
### Ignorer l'appel d'une méthode
```java
        doNothing().when(myMock).method();
```
## 3. Vérification des Appels

### Vérifier le nombre d'appels
```java
        verify(myMock, times(2)).method();
```
### Vérifier qu'une méthode n'a jamais été appelée
```java
        verify(myMock, never()).method();
```
### Vérifier qu'une méthode a été appelée au moins une fois
```java
        verify(myMock, atLeastOnce()).method();
```
## 4. Argument Matchers

### Matcher pour tout argument de type spécifié
```java
        when(myMock.method(any(String.class))).thenReturn(expectedValue);
```
### Matcher pour une valeur spécifique
```java
        when(myMock.method(eq(expectedArg))).thenReturn(expectedValue);
```
### Capturer un argument pour une vérification ultérieure
```java
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(myMock).method(captor.capture());
```
## 5. Reset du Mock

### Réinitialiser un mock pour le réutiliser

        reset(myMock);

# Pense-Bête MockMvc en Java

## 1. Configuration de MockMvc

### Configurer avec un contrôleur existant
```java
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
```
### Configurer avec SpringBootTest
```java
        @Autowired
        private MockMvc mockMvc;
```
## 2. Envoyer des Requêtes

### GET Request
```java
        mockMvc.perform(MockMvcRequestBuilders.get("/endpoint")).andExpect(status().isOk());
```
### POST Request
```java
        mockMvc.perform(MockMvcRequestBuilders.post("/endpoint")
                .param("paramName", "paramValue"))
                .andExpect(status().isOk());
```
## 3. Assertions de Réponse

### Vérifier le Statut
```java
        andExpect(status().isOk());
```
### Vérifier le Contenu
```java
        andExpect(content().string("Expected Content"));
```
### Vérifier les Attributs du Modèle
```java
        andExpect(model().attribute("attributeName", "expectedValue"));
```
## 4. Assertions de Vue (View)

### Vérifier le Nom de la Vue
```java
        andExpect(view().name("expectedViewName"));
```
### Vérifier l'Existence d'une Vue
```java
        andExpect(view().exists());
```
## 5. Assertions de Redirection

### Vérifier une Redirection
```java
        andExpect(redirectedUrl("/newEndpoint"));
```
### Vérifier une Redirection Temporaire
```java
        andExpect(redirectedUrlTemporary("/newEndpoint"));
```
# Tests d'Endpoints REST

## Envoyer des Requêtes pour Tester les Endpoints REST

### GET Request
```java
        mockMvc.perform(MockMvcRequestBuilders.get("/api/resource")).andExpect(status().isOk());
```
### POST Request
```java
        mockMvc.perform(MockMvcRequestBuilders.post("/api/resource")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"key\": \"value\" }"))
                .andExpect(status().isCreated());
```
## Assertions de Réponse pour les API REST

### Vérifier le Contenu JSON
```java
        mockMvc.perform(MockMvcRequestBuilders.get("/api/resource"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.key").value("expectedValue"));
```
### Vérifier le Statut de Réponse
```java
        mockMvc.perform(MockMvcRequestBuilders.get("/api/resource")).andExpect(status().isOk());
```
### Vérifier les Headers de Réponse
```java
        mockMvc.perform(MockMvcRequestBuilders.get("/api/resource")).andExpect(header().string("customHeader", "expectedValue"));
```