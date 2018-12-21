#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Dec 21 11:18:50 2018

@author: lappv
"""

from nltk.corpus import stopwords
from nltk.stem.porter import PorterStemmer
import re
import pickle
import os
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer

baseDir = os.path.abspath(os.path.dirname(__file__))
MODEL_FILE = baseDir + '/../training/naive_model.pickle'
TRAINING_DATA_FILE = baseDir + '/../training/Restaurant_Reviews.tsv'

def preprocess(text):
    """ Preprocess text
    
    Parameters
    ----------
    text: a raw sentences
    
    Returns
    -------
    string: text processed
    """
    #ignore special characters, lower text
    text = re.sub('[^a-zA-Z]', ' ', text)
    text = text.lower()
    
    #stemming, ignore stop words
    words = text.split()
    ps = PorterStemmer()
    words = [ps.stem(word) for word in words if not word in set(stopwords.words('english'))]
    
    #join words become sentence
    text = ' '.join(words)
    
    return text

def genFeature(text, cv):
    """ generate feature vector
    
    Parameters
    ----------
    text: a raw sentences
    cv: sklearn.feature_extraction.text.CountVectorizer object
    
    Returns
    --------
    array
    
    """
    text = preprocess(text)
    
    return cv.transform([text]).toarray()

def processTrainingData():
    dataset = pd.read_csv(TRAINING_DATA_FILE, delimiter = '\t')
    corpus = []
    for i in range(0, len(dataset)):
        corpus.append(preprocess(dataset['Review'][i]))
        
    cv = CountVectorizer(max_features=1500)
    X = cv.fit_transform(corpus).toarray()
    y = dataset.iloc[:, 1].values
    
    return cv, X, y

def loadTrainingData():
    """Load training data
    
    Parameters
    ---------
    file: file path, file format in csv or tsv
    
    Returns
    -------
    """
    td = processTrainingData()
    return td[1], td[2]
    
    
def loadFeatureMethod():
    td = processTrainingData()
    return td[0]
    

def saveModel(model):
    file = open(MODEL_FILE, 'wb')
    pickle.dump(model, file)
    file.close()
    
def loadModel():
    file = open(MODEL_FILE, 'rb')
    model = pickle.load(file)
    file.close()
    return model
   